import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserSelectComponent } from './user-select/user-select.component';
import { ChatListComponent } from './chat-list/chat-list.component';
import { ChatRoomComponent } from './chat-room/chat-room.component';
import { LogoutComponent } from './logout/logout.component';

export const routes: Routes = [
  { path: '', component: UserSelectComponent },
  { path: 'chats', component: ChatListComponent },
  { path: 'chat/:id', component: ChatRoomComponent },
  { path: 'logout', component: LogoutComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
