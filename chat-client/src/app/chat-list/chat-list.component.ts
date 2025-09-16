import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { CustomerService } from '../models/customer-service.model';

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

    this.http.get<any[]>(`/api/chatsessions`).subscribe(data => {
      if (this.user.isAdvisor) {
        // Un conseiller voit toutes les conversations où il est advisor
        this.chats = data.filter(chat => chat.advisor && chat.advisor.id === this.user.id);
      } else {
        // Un utilisateur voit toutes ses conversations et peut en créer
        this.chats = data.filter(chat => chat.client && chat.client.id === this.user.id);
      }
    });

    this.http.get<CustomerService[]>('/api/customer-services')
      .subscribe(data => {
        this.advisors = data;
        console.log('Conseillers récupérés:', this.advisors);
      }, err => {
        console.error('Erreur récupération conseillers:', err);
      });
  }

  openChat(chat: any) {
    this.router.navigate(['/chat', chat.id]);
  }

  logout() {
    localStorage.removeItem('user');
    this.router.navigate(['/']);
  }

  createChat() {
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
    if (this.user.isAdvisor) return; // Un conseiller ne peut pas créer de conversation
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
