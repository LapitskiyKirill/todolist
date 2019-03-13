import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AuthenticationUser} from '../dto/AuthenticationUser';
import {Observable} from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {

    constructor(private http: HttpClient) {
    }

    public authenticate(authenticationUser: AuthenticationUser): Observable<string> {
        return this.http.post<string>('http://localhost:8080/authenticate', authenticationUser, {responseType: 'text'});
    }
}
