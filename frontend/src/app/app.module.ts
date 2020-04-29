import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {HttpClientModule} from '@angular/common/http';
// import {Ng4LoadingSpinnerModule} from "ng4-loading-spinner";
import {RouterModule, Routes} from '@angular/router';
import {UserComponent} from './component/user/user.component';
import {HeaderComponent} from './component/header/components/header.component';
import {BanComponent} from './component/ban/ban.component';
import {CommentsComponent} from './component/comments/comments.component';
import {PostComponent} from './component/post/post.component';
import {TagComponent} from './component/tag/tag.component';
import {UserHomePageComponent} from './component/user-home-page/user-home-page.component';
import {LoginComponent} from './component/login/login.component';
import {RegisterComponent} from './component/register/register.component';
import {SinglePostComponent} from './component/single-post/single-post.component';
import {TestHeaderComponent} from './component/test-header/test-header.component';
import {MDBBootstrapModule} from 'angular-bootstrap-md';

const appRoutes: Routes = [
  {path: '#', component: UserComponent},
  {path: 'ban', component: BanComponent},
  {path: 'comments', component: CommentsComponent},
  {path: 'posts', component: PostComponent},
  {path: 'tag', component: TagComponent},
  {path: 'user', component: UserComponent},
  {path: 'home', component: UserHomePageComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'home/post/:id', component: SinglePostComponent},
  {path: 'posts/post/:id', component: SinglePostComponent},
];



@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    HeaderComponent,
    BanComponent,
    CommentsComponent,
    PostComponent,
    TagComponent,
    UserHomePageComponent,
    LoginComponent,
    RegisterComponent,
    SinglePostComponent,
    TestHeaderComponent,
  ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule,
        // Ng4LoadingSpinnerModule.forRoot(),
        MDBBootstrapModule.forRoot(),
        RouterModule.forRoot(appRoutes),
        ReactiveFormsModule,
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

