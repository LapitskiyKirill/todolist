import {Injectable} from '@angular/core';
import {AuthUser} from '../Entities/AuthUser';
import {HttpClient} from '@angular/common/http';
import {SERVER_PATH} from '../../globals';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {
  }

  auth(authUser: AuthUser): Observable<string> {
    return this.http.post<string>(SERVER_PATH + '/authenticate', authUser, {responseType: 'text' as 'json'});

  }
}
