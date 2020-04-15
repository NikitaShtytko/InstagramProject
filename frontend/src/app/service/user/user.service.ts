import {User} from "../../models/user";
import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})

export class UserService {

  constructor(private httpClient: HttpClient) {}

  getUsers(): Observable<User[]> {
    return this.httpClient.get<User[]>("http://localhost:8080/api/users");
  }
}


// import {Injectable} from '@angular/core';
// import {Observable, throwError} from "rxjs";
// import {User} from "../models/user";
// import {Wallet} from "../models/wallet";
// import {UserSubscription} from "../models/userubscriptions";
// import {BsModalRef, BsModalService} from "ngx-bootstrap";
// import {HttpClient, HttpResponse} from "@angular/common/http";
// import {catchError} from "rxjs/operators";
// import {Login} from "../models/login";
// import {UserModel} from "../models/user-model";




//   url: string = "http://localhost:8080/userinfo";
//
//
//   getUsers(): Observable<User[]> {
//     return this.httpClient.get<User[]>("http://localhost:8081/api/user");
//   }
//
//   getUserById(id: number): Observable<User>{
//     return this.httpClient.get<User>("http://localhost:8081/userinfo/get/" + id);
//   }
//
//   saveUser(user: User): Observable<User> {
//     return this.httpClient.post<User>("http://localhost:8081/userinfo" , user);
//   }
//
//   updateUser(user: User): Observable<User> {
//     return this.httpClient.put<User>("http://localhost:8081/userinfo" , user);
//   }
//   // addUser(user: User, user_id: number): Observable<HttpResponse<User>> {
//   //   return this.httpClient.put<User>(this.url +'/' + user_id ,  user, {observe: "response"});
//   // }
//
//   deleteUser(user_id: number): Observable<User> {
//     return this.httpClient.delete<User>("http://localhost:8081/userinfo/" + user_id);
//   }
//
//   updateWallet(wallet: Wallet): Observable<Wallet> {
//     return this.httpClient.put<Wallet>("http://localhost:8080/wallet" , wallet);
//   }
//
//   getUserSubscriptions(id: any): Observable<UserSubscription[]>{
//     return this.httpClient.get<UserSubscription[]>("http://localhost:8081/subscribes/get/" + id);
//   }
//
//   getUserSubscriptionsService(id: string): Observable<UserSubscription[]>{
//     return this.httpClient.get<UserSubscription[]>("http://localhost:8081/subscribes/get/" + id);
//   }
//
//   changeServiceStatus(subscription: UserSubscription): Observable<UserSubscription>{
//     return this.httpClient.put<UserSubscription>("http://localhost:8081/subscribes/put", subscription);
//   }
//
//
// }
