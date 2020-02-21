import {Injectable} from '@angular/core';
import {RegisterUser} from '../Entities/RegisterUser';
import {HttpClient} from '@angular/common/http';
import {SERVER_PATH} from '../../globals';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http: HttpClient) {
  }

  register(registerUser: RegisterUser) {
    return this.http.post(SERVER_PATH + '/register', registerUser);
  }
}
