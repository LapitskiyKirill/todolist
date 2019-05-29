import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SERVER_PATH} from '../../globals';
import {NewScheduled} from '../dto/NewScheduled';
import {Scheduled} from '../dto/Scheduled';

@Injectable({
    providedIn: 'root'
})
export class ScheduledService {

    constructor(private http: HttpClient) {
    }

    create(token: string, newScheduled: NewScheduled): Observable<Scheduled> {
        return this.http.post<Scheduled>(SERVER_PATH + '/scheduled/create', newScheduled, {
            headers: {
                token: token
            }
        });
    }

    delete(token: string, taskId: number) {
        return this.http.get(SERVER_PATH + '/scheduled/delete', {
            headers: {
                token: token
            },
            params: {
                taskId: taskId.toString()
            }
        });
    }

    getAll(token: string): Observable<Scheduled[]> {
        return this.http.get<Scheduled[]>(SERVER_PATH + '/scheduled/getAll', {
            headers: {
                token: token
            }
        });
    }
}
