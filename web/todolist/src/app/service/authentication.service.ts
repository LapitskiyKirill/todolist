import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AuthenticationUser} from '../dto/AuthenticationUser';
import {Observable} from 'rxjs';
import {SERVER_PATH} from '../../globals';
import {User} from '../dto/User';

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {

    constructor(private http: HttpClient) {
    }

    public authenticate(authenticationUser: AuthenticationUser): Observable<string> {
        return this.http.post<string>(SERVER_PATH + '/authenticate', authenticationUser, {responseType: 'text' as 'json'});
    }

    public validate(token: string): Observable<User> {
        return this.http.get<User>(SERVER_PATH + '/authenticate/validate', {
            params: {
                token: token
            }
        });
    }
}
