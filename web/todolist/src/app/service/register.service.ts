import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AuthenticationUser} from '../dto/AuthenticationUser';
import {RegisterUser} from '../dto/RegisterUser';
import {SERVER_PATH} from '../../globals';

@Injectable({
    providedIn: 'root'
})
export class RegisterService {

    constructor(private http: HttpClient) {
    }

    public register(registerUser: RegisterUser) {
        return this.http.post(SERVER_PATH + '/register', registerUser);
    }
}
