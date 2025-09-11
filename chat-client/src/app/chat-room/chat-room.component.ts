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
  newMessage: string = '';

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private chatSocket: ChatSocketService,
    private router: Router
  ) {}

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('user') || '{}');
    this.chatSessionId = Number(this.route.snapshot.paramMap.get('id'));
    this.http.get<any>(`/api/chatsessions/${this.chatSessionId}`).subscribe(chat => {
      this.advisor = this.user.role.name === 'USER' ? chat.advisor : chat.client;
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
    const msg: ChatMessage = {
      content: this.newMessage,
      sender: this.user.username,
      receiver: this.advisor?.username,
      chatSessionId: this.chatSessionId
    };
    this.chatSocket.sendMessage(msg);
    this.newMessage = '';
  }

  backToList() {
    this.router.navigate(['/chats']);
  }

  getSenderName(sender: any): string {
    return typeof sender === 'string' ? sender : sender.firstName;
  }

  isToday(date: string | undefined): boolean {
    if (!date) return false;
    const d = new Date(date);
    const now = new Date();
    return d.getDate() === now.getDate() && d.getMonth() === now.getMonth() && d.getFullYear() === now.getFullYear();
  }
}
