import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AuthenticationUser} from '../dto/AuthenticationUser';
import {Observable} from 'rxjs';
import {SERVER_PATH} from '../../globals';

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {

    constructor(private http: HttpClient) {
    }

    public authenticate(authenticationUser: AuthenticationUser): Observable<string> {
        return this.http.post<string>(SERVER_PATH + '/authenticate', authenticationUser, {responseType: 'text' as 'json'});
    }
}
