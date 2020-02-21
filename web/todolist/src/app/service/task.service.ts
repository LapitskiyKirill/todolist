import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Task} from '../dto/Task';
import {SERVER_PATH} from '../../globals';
import {EditTask} from '../dto/EditTask';
import {NewTask} from '../dto/NewTask';

@Injectable({
    providedIn: 'root'
})
export class TaskService {

    constructor(
        private http: HttpClient
    ) {
    }

    create(token: string, newTask: NewTask): Observable<Task> {
        return this.http.post<Task>(SERVER_PATH + '/task/create', newTask, {
            headers: {
                token: token
            }
        });
    }

    public getAll(token: string): Observable<Task[]> {
        return this.http.get<Task[]>(SERVER_PATH + '/task/getAll', {
            headers: {
                token: token
            }
        });
    }

    public getByCategory(token: string, category: string): Observable<Task[]> {
        return this.http.get<Task[]>(SERVER_PATH + '/task/getByCategory', {
            headers: {
                token: token
            },
            params: {
                category: category
            }
        });
    }

    check(token: string, taskId: number) {
        return this.http.get<void>(SERVER_PATH + '/task/check', {
            headers: {
                token: token
            },
            params: {
                taskId: taskId.toString()
            }
        });
    }

    unCheck(token: string, taskId: number) {
        return this.http.get<void>(SERVER_PATH + '/task/uncheck', {
            headers: {
                token: token
            },
            params: {
                taskId: taskId.toString()
            }
        });
    }

    delete(token: string, taskId: number) {
        return this.http.get(SERVER_PATH + '/task/delete', {
            headers: {
                token: token
            },
            params: {
                taskId: taskId.toString()
            }
        });
    }

    edit(token: string, taskId: number, task: EditTask) {
        return this.http.post<Task>(SERVER_PATH + '/task/edit', task, {
            headers: {
                token: token
            },
            params: {
                taskId: taskId.toString()
            }
        });
    }
}
