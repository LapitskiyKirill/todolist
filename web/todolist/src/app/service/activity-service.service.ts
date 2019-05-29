import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {NewScheduled} from '../dto/NewScheduled';
import {Observable} from 'rxjs';
import {Scheduled} from '../dto/Scheduled';
import {SERVER_PATH} from '../../globals';
import {observableToBeFn} from 'rxjs/internal/testing/TestScheduler';
import {Activity} from '../dto/Activity';
import {Task} from '../dto/Task';

@Injectable({
    providedIn: 'root'
})
export class ActivityServiceService {

    constructor(private http: HttpClient) {
    }

    complete(token: string, id: number) {
        return this.http.get(SERVER_PATH + '/activity/complete', {
            headers: {
                token: token
            },
            params: {
                scheduledActivityId: id.toString()
            }
        });
    }

    uncheck(token: string, id: number) {
        return this.http.get(SERVER_PATH + '/activity/uncheck', {
            headers: {
                token: token
            },
            params: {
                scheduledActivityId: id.toString()
            }
        });
    }

    public getTodaysActivities(token: string): Observable<Activity[]> {
        return this.http.get<Activity[]>(SERVER_PATH + '/activity/todays', {
            headers: {
                token: token
            }
        });
    }
}


