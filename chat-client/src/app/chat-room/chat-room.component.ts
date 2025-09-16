import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { ChatSocketService, ChatMessage } from '../services/chat-socket.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-chat-room',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './chat-room.component.html',
  styleUrls: ['./chat-room.component.scss'],
})
export class ChatRoomComponent implements OnInit {
  messages: ChatMessage[] = [];
  chatSessionId!: number;
  user: any;
  advisor: any;
  client: any;
  newMessage: string = '';

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private chatSocket: ChatSocketService,
    private router: Router
  ) {}

  ngOnInit() {
    // On récupère soit un user, soit un customer service (conseiller)
    const userRaw = localStorage.getItem('user');
    const advisorRaw = localStorage.getItem('advisor');
    if (advisorRaw) {
      this.user = JSON.parse(advisorRaw);
      this.user.isAdvisor = true;
    } else if (userRaw) {
      this.user = JSON.parse(userRaw);
      this.user.isAdvisor = false;
    } else {
      this.user = {};
      this.user.isAdvisor = false;
    }
    this.chatSessionId = Number(this.route.snapshot.paramMap.get('id'));
    this.http.get<any>(`/api/chatsessions/${this.chatSessionId}`).subscribe(chat => {
      if (this.user.isAdvisor) {
        this.advisor = this.user;
        this.client = chat.client;
      } else {
        this.advisor = chat.advisor;
        this.client = this.user;
      }
    });
    this.http.get<ChatMessage[]>(`/api/messages?chatSessionId=${this.chatSessionId}`).subscribe(data => {
      this.messages = data;
    });
    this.chatSocket.messages$.subscribe((msg: ChatMessage | null) => {
      if (msg && msg.chatSessionId === this.chatSessionId) {
        this.messages.push(msg);
      }
    });
  }

  sendMessage() {
    let msg: any = {
      content: this.newMessage,
      chatSessionId: this.chatSessionId
    };
    if (this.user.isAdvisor) {
      msg.senderCustomerService = this.user;
      msg.receiverUser = this.client;
    } else {
      msg.senderUser = this.user;
      msg.receiverCustomerService = this.advisor;
    }
    this.chatSocket.sendMessage(msg);
    this.newMessage = '';
  }
  getSenderName(sender: any): string {
    if (typeof sender === 'object' && sender !== null) {
      return sender.firstName || sender.username || '[inconnu]';
    }
    return sender;
  }

  backToList() {
    this.router.navigate(['/chats']);
  }


  isToday(date: string | undefined): boolean {
    if (!date) return false;
    const d = new Date(date);
    const now = new Date();
    return d.getDate() === now.getDate() && d.getMonth() === now.getMonth() && d.getFullYear() === now.getFullYear();
  }
}
