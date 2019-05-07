import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Task} from '../dto/Task';
import {SERVER_PATH} from '../../globals';

@Injectable({
    providedIn: 'root'
})
export class TaskService {

    constructor(
        private http: HttpClient
    ) {
    }

    getAll(token: string): Observable<Task[]> {
        return this.http.get<Task[]>(SERVER_PATH + '/task/getAll', {
            headers: {
                token: token
            }
        });
    }

    getByCategory(token: string, category: string): Observable<Task[]> {
        return this.http.get<Task[]>(SERVER_PATH + '/task/getByCategory', {
            headers: {
                token: token
            },
            params: {
                category: category
            }
        });
    }

}
