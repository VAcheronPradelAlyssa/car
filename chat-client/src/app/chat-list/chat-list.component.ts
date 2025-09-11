import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-chat-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './chat-list.component.html',
  styleUrls: ['./chat-list.component.scss']
})
export class ChatListComponent implements OnInit {
  chats: any[] = [];
  user: any;
  advisors: any[] = [];

  constructor(private router: Router, private http: HttpClient) {}

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('user') || '{}');
    this.http.get<any[]>(`/api/chatsessions`).subscribe(data => {
      this.chats = data.filter(chat =>
        (chat.client && chat.client.id === this.user.id) ||
        (chat.advisor && chat.advisor.id === this.user.id)
      );
    });
    if (this.user.role.name === 'USER') {
      this.http.get<any[]>('/api/users/active-advisors').subscribe(data => {
        this.advisors = data;
      });
    }
  }

  openChat(chat: any) {
    this.router.navigate(['/chat', chat.id]);
  }

  logout() {
    localStorage.removeItem('user');
    this.router.navigate(['/']);
  }

  createChat() {
    // Récupérer un conseiller actif aléatoire
    this.http.get<any[]>('/api/users/active-advisors').subscribe(advisors => {
      if (advisors.length === 0) {
        alert('Aucun conseiller actif disponible.');
        return;
      }
      const advisor = advisors[Math.floor(Math.random() * advisors.length)];
      const chatSession = {
        client: this.user,
        advisor: advisor
      };
      this.http.post<any>('/api/chatsessions', chatSession).subscribe(newChat => {
        this.chats.push(newChat);
        this.openChat(newChat);
      });
    });
  }

  createChatWithAdvisor(advisor: any) {
    const chatSession = {
      client: this.user,
      advisor: advisor
    };
    this.http.post<any>('/api/chatsessions', chatSession).subscribe(newChat => {
      this.chats.push(newChat);
      this.openChat(newChat);
    });
  }
}
