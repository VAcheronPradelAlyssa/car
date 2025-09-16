import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { CustomerService } from '../models/customer-service.model';

@Component({
  selector: 'app-user-select',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './user-select.component.html',
  styleUrls: ['./user-select.component.scss']
})
export class UserSelectComponent implements OnInit {
  users: any[] = [];
  advisors: CustomerService[] = [];
  nonActiveAdvisors: CustomerService[] = [];
  selectedUser: any;
  selectedAdvisor: CustomerService | null = null;

  constructor(private router: Router, private http: HttpClient) {}

  ngOnInit() {
    this.http.get<any[]>('/api/users').subscribe(data => {
      this.users = data;
    });
    this.http.get<CustomerService[]>('/api/customer-services').subscribe(data => {
      this.advisors = data.filter(a => a.status === 'ACTIVE');
      this.nonActiveAdvisors = data.filter(a => a.status !== 'ACTIVE');
    });
  }

  selectUser(user: any) {
  this.selectedUser = user;
  localStorage.setItem('user', JSON.stringify(user));
  localStorage.removeItem('advisor');
  this.router.navigate(['/chats']);
  }

  selectAdvisor(advisor: CustomerService) {
  this.selectedAdvisor = advisor;
  localStorage.setItem('advisor', JSON.stringify(advisor));
  localStorage.removeItem('user');
  this.router.navigate(['/chats']);
  }
}
