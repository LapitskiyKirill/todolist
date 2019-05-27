import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SERVER_PATH} from '../../globals';
import {Observable} from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class TokenService {

    constructor(private http: HttpClient
    ) {
    }

    remove(token: string): Observable<void> {
        console.log("logout1");
        return this.http.get<void>(SERVER_PATH + '/token/delete', {
            params: {
                token: token
            }
        });
    }
}
