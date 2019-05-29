import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Category} from '../../dto/Category';
import {CategoryService} from '../../service/category.service';
import {TokenProvider} from '../../provider/token.provider';
import {Task} from 'src/app/dto/Task';
import {TaskService} from '../../service/task.service';
import {AppComponent} from '../../app.component';
import * as moment from 'moment';
import {ScheduledService} from '../../service/scheduled.service';

@Component({
    selector: 'app-task-info',
    templateUrl: './task-info.component.html',
    styleUrls: ['./task-info.component.scss']
})
export class TaskInfoComponent implements OnInit {
    categories: Category[];
    taskId: number;
    task: Task;
    isScheduled: boolean;
    isDeadline = false;

    constructor(private app: AppComponent,
                private activatedRoute: ActivatedRoute,
                private categoryService: CategoryService,
                private taskService: TaskService,
                private tokenProvider: TokenProvider,
                private router: Router,
                private scheduledService: ScheduledService
    ) {
    }

    delete() {
        this.taskService.delete(localStorage.getItem('token'), this.taskId).subscribe(
            () => this.router.navigate(['tasks']));

    }

    check() {
        this.tokenProvider.token.subscribe(t => {
            this.taskService.check(t, this.taskId).subscribe();
        });
    }

    uncheck() {
        this.tokenProvider.token.subscribe(t => {
            this.taskService.unCheck(t, this.taskId).subscribe();
        });
    }

    deleteSchedule() {
        this.tokenProvider.token.subscribe(t => {
            this.scheduledService.delete(t, this.taskId).subscribe(() => {
                this.router.navigate(['tasks']);
            });
        });
    }


    ngOnInit() {
        this.app.onLoad(() => {
            this.activatedRoute.params.subscribe(params => this.taskId = params['taskId']);
            this.tokenProvider.token.subscribe(t => {
                this.categoryService.getCategories(t).subscribe(cs => {
                    this.categories = cs;
                });
                this.taskService.getAll(t).subscribe(ts => {
                    ts.forEach(task => {
                        if (task.id == this.taskId) {
                            this.task = task;
                            if ((moment(moment(Date.now()).format('YYYY-MM-DD')).isAfter(task.deadline)
                                || moment(Date.now()).format('YYYY-MM-DD') === (moment(task.deadline).format('YYYY-MM-DD')))
                                && this.task.completed === null) {
                                this.isDeadline = true;
                            }
                        }
                    });
                });
            });
            this.isScheduled = false;
            this.scheduledService.getAll(localStorage.getItem('token')).subscribe(s => {
                s.forEach(scheduled => {
                    if (scheduled.taskId == this.taskId) {
                        this.isScheduled = true;
                    }
                });
            });
            console.log(this.isScheduled);
        });
    }
}
