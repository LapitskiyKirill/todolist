import {Component, OnInit} from '@angular/core';
import {Scheduled} from '../../dto/Scheduled';
import * as moment from '../all-tasks/all-tasks.component';
import {AppComponent} from '../../app.component';
import {ActivatedRoute, Router} from '@angular/router';
import {CategoryService} from '../../service/category.service';
import {TokenProvider} from '../../provider/token.provider';
import {TaskService} from '../../service/task.service';
import {Task} from '../../dto/Task';
import {ScheduledService} from '../../service/scheduled.service';
import {forEach} from '@angular/router/src/utils/collection';
import {ScheduledTask} from '../../dto/ScheduledTask';

@Component({
    selector: 'app-scheduleds',
    templateUrl: './scheduleds.component.html',
    styleUrls: ['./scheduleds.component.scss']
})
export class ScheduledsComponent implements OnInit {
    scheduleds: Scheduled[] = [];
    tasks: Task[] = [];
    scheduledTasks: ScheduledTask[] = [];

    constructor(private app: AppComponent,
                private activatedRoute: ActivatedRoute,
                private categoryService: CategoryService,
                private tokenProvider: TokenProvider,
                private scheduledService: ScheduledService,
                private taskService: TaskService
    ) {
    }

    ngOnInit() {
        this.app.onLoad(() => {
            this.tokenProvider.token.subscribe(t => {
                this.taskService.getAll(t).subscribe(ts => {
                    this.tasks = ts;
                    this.scheduledService.getAll(t).subscribe(scheduleds => {
                        scheduleds.forEach(sch => {
                            if (sch.deleted === false) {
                                this.scheduleds.push(sch);
                            }
                        });
                        this.scheduleds.forEach(scheduled => {

                            this.tasks.forEach(task => {
                                if (task.id === scheduled.taskId) {
                                    this.scheduledTasks.push(new ScheduledTask(scheduled.id, task.text, scheduled.from, scheduled.periodicity));
                                }
                            });
                        });
                    });
                });
            });
        });
    }
}
