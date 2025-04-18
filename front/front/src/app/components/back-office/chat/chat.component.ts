import { Component, OnInit, OnDestroy, ViewChild, ElementRef } from '@angular/core';
import { Subject, takeUntil } from 'rxjs';
import { ChatService } from 'src/app/services/chat.service';
import { DatePipe } from '@angular/common';

interface Message {
  role: string;
  content: string;
  createdAt?: Date;
}

interface Conversation {
  id: string;
  messages: Message[];
  title?: string;
}

interface ApiResponse {
  choices: Array<{
    message: {
      content: string;
    };
  }>;
}

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css'],
  providers: [DatePipe]
})
export class ChatComponent implements OnInit, OnDestroy {
  @ViewChild('messagesEnd') private messagesEnd!: ElementRef;

  messages: Message[] = [];
  input = '';
  isLoading = false;
  conversations: Conversation[] = [];
  selectedConversationId: string | null = null;
  showDeleteModal = false;
  conversationToDelete: string | null = null;
  isDeleting = false;
  private destroy$ = new Subject<void>();

  constructor(private chatService: ChatService) {}

  ngOnInit() {
    this.loadConversations();
    this.chatService.currentConversation$
      .pipe(takeUntil(this.destroy$))
      .subscribe(conv => {
        //@ts-ignore
        this.messages = conv?.messages || [];
        this.selectedConversationId = conv?.id || null;
      });
  }

  loadConversations() {
    this.chatService.getConversations()
      .pipe(takeUntil(this.destroy$))
      //@ts-ignore
      .subscribe(convs => this.conversations = convs);
  }

  createNewConversation() {
    this.chatService.setCurrentConversation(null);
    this.messages = [];
    this.input = '';
  }

  async onSubmit(): Promise<void> {
    if (!this.input.trim()) return;

    const userMessage: Message = {
      role: 'user',
      content: this.input.trim()
    };

    this.isLoading = true;
    const tempMessages = [...this.messages, userMessage];
    this.messages = tempMessages;
    this.input = '';

    try {
      const response = await this.chatService.sendMessage(
        tempMessages,
        this.selectedConversationId || undefined
      ).toPromise() as ApiResponse;

      if (response?.choices?.[0]?.message?.content) {
        this.messages = [...tempMessages, {
          role: 'assistant',
          content: response.choices[0].message.content,
          createdAt: new Date()
        }];

        if (!this.selectedConversationId) {
          this.loadConversations();
        }
      } else {
        throw new Error('Format de réponse invalide');
      }
    } catch (error: any) {
      console.error('Erreur lors de l\'envoi du message:', error);
      this.messages = [...tempMessages, {
        role: 'assistant',
        content: error.message || 'Une erreur est survenue lors de l\'envoi du message',
        createdAt: new Date()
      }];
    } finally {
      this.isLoading = false;
      this.scrollToBottom();
    }
  }

  selectConversation(id: string) {
    this.chatService.getConversation(id)
      .pipe(takeUntil(this.destroy$))
      .subscribe(conv => {
        this.chatService.setCurrentConversation(conv);
        this.scrollToBottom();
      });
  }

  requestDelete(id: string) {
    this.conversationToDelete = id;
    this.showDeleteModal = true;
  }

  async confirmDelete() {
    if (!this.conversationToDelete) return;

    this.isDeleting = true;
    try {
      await this.chatService.deleteConversation(this.conversationToDelete).toPromise();
      this.conversations = this.conversations.filter(c => c.id !== this.conversationToDelete);

      if (this.selectedConversationId === this.conversationToDelete) {
        this.createNewConversation();
      }
    } catch (error) {
      console.error('Delete error:', error);
    } finally {
      this.isDeleting = false;
      this.showDeleteModal = false;
      this.conversationToDelete = null;
    }
  }

  scrollToBottom() {
    setTimeout(() => {
      this.messagesEnd?.nativeElement.scrollIntoView({ behavior: 'smooth' });
    }, 100);
  }

  isUserMessage(message: Message): boolean {
    return message.role.toLowerCase() === 'user';
  }

  formatMessage(content: string): string {
    if (!content) return '';
    
    // Convert newlines to <br> tags
    let formattedContent = content.replace(/\n/g, '<br>');
    
    // Convert bullet points
    formattedContent = formattedContent.replace(/^[-•]\s/gm, '• ');
    
    // Convert numbered lists
    formattedContent = formattedContent.replace(/^\d+\.\s/gm, (match) => `<strong>${match}</strong>`);
    
    return formattedContent;
  }

  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
