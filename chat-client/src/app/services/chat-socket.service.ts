import { Injectable } from '@angular/core';
import { Client, Message, StompSubscription } from '@stomp/stompjs';
import { BehaviorSubject } from 'rxjs';

export interface ChatMessage {
  content: string;
  sender?: string;
  receiver?: string;
  senderUser?: any;
  senderCustomerService?: any;
  receiverUser?: any;
  receiverCustomerService?: any;
  chatSessionId?: number;
  timestamp?: string;
}

@Injectable({ providedIn: 'root' })
export class ChatSocketService {
  private stompClient: Client;
  private messagesSubject = new BehaviorSubject<ChatMessage | null>(null);
  public messages$ = this.messagesSubject.asObservable();
  private subscription?: StompSubscription;

  constructor() {
    this.stompClient = new Client({
      brokerURL: 'ws://localhost:8080/ws-chat/websocket', // WebSocket natif
      reconnectDelay: 5000,
    });
    this.stompClient.onConnect = () => {
      this.subscription = this.stompClient.subscribe('/topic/public', (msg: Message) => {
        this.messagesSubject.next(JSON.parse(msg.body));
      });
    };
    this.stompClient.activate();
  }

  sendMessage(message: ChatMessage) {
    this.stompClient.publish({
      destination: '/app/chat.sendMessage',
      body: JSON.stringify(message)
    });
  }
}
