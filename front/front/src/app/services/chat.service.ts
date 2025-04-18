import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';

interface Message {
  role: string;
  content: string;
  createdAt?: Date;
}

interface Conversation {
  id: string;
  title: string;
  createdAt: Date;
  messages: Message[];
}

interface ApiResponse {
  choices: Array<{
    message: {
      content: string;
    };
  }>;
}

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private apiUrl = 'http://localhost:8222/api'; // Port configuré ici
  private currentConversationSubject = new BehaviorSubject<Conversation | null>(null);
  currentConversation$ = this.currentConversationSubject.asObservable();

  constructor(private http: HttpClient) {}

  setCurrentConversation(conversation: Conversation | null) {
    this.currentConversationSubject.next(conversation);
  }

  getConversations(): Observable<Conversation[]> {
    // Appel à la bonne URL
    return this.http.get<Conversation[]>(`${this.apiUrl}/conversations`); // Correctif ici
  }

  getConversation(id: string): Observable<Conversation> {
    return this.http.get<Conversation>(`${this.apiUrl}/conversations/${id}`); // Utilisation de apiUrl
  }

  sendMessage(messages: Message[], conversationId?: string): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(`${this.apiUrl}/chat`, { messages, conversationId }); // Utilisation de apiUrl
  }

  deleteConversation(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/conversations/${id}`); // Utilisation de apiUrl
  }
}