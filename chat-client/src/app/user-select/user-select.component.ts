import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-user-select',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './user-select.component.html',
  styleUrls: ['./user-select.component.scss']
})
export class UserSelectComponent implements OnInit {
  users: any[] = [];
  advisors: any[] = [];
  nonActiveAdvisors: any[] = [];
  selectedUser: any;
  selectedAdvisor: any;

  constructor(private router: Router, private http: HttpClient) {}

  ngOnInit() {
    this.http.get<any[]>('/api/users').subscribe(data => {
      this.users = data.filter(u => u.role.name === 'USER');
      this.nonActiveAdvisors = data.filter(u => u.role.name === 'ADVISOR' && u.status !== 'ACTIVE');
    });
    this.http.get<any[]>('/api/users/active-advisors').subscribe(data => {
      this.advisors = data;
    });
  }

  selectUser(user: any) {
    this.selectedUser = user;
    localStorage.setItem('user', JSON.stringify(user));
    this.router.navigate(['/chats']);
  }

  selectAdvisor(advisor: any) {
    this.selectedUser = advisor;
    localStorage.setItem('user', JSON.stringify(advisor));
    this.router.navigate(['/chats']);
  }
}
