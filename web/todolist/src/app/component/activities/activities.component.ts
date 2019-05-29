import {Component, OnInit} from '@angular/core';
import {Scheduled} from '../../dto/Scheduled';
import {Task} from '../../dto/Task';
import {ScheduledTask} from '../../dto/ScheduledTask';
import {AppComponent} from '../../app.component';
import {ActivatedRoute} from '@angular/router';
import {CategoryService} from '../../service/category.service';
import {TokenProvider} from '../../provider/token.provider';
import {ScheduledService} from '../../service/scheduled.service';
import {TaskService} from '../../service/task.service';
import {Activity} from '../../dto/Activity';
import {ActivityUser} from '../../dto/ActivityUser';
import {ActivityServiceService} from '../../service/activity-service.service';

@Component({
    selector: 'app-activities',
    templateUrl: './activities.component.html',
    styleUrls: ['./activities.component.scss']
})
export class ActivitiesComponent implements OnInit {
    scheduleds: Scheduled[] = [];
    tasks: Task[] = [];
    scheduledTasks: ScheduledTask[] = [];
    active: Activity[] = [];
    activities: ActivityUser[] = [];

    constructor(private app: AppComponent,
                private activatedRoute: ActivatedRoute,
                private categoryService: CategoryService,
                private tokenProvider: TokenProvider,
                private scheduledService: ScheduledService,
                private taskService: TaskService,
                private activityServiceService: ActivityServiceService
    ) {
    }

    getTodays() {
        this.activityServiceService.getTodaysActivities(localStorage.getItem('token')).subscribe(active => this.active = active);
    }

    ngOnInit() {
        this.app.onLoad(() => {
            this.getTodays();
            this.tokenProvider.token.subscribe(t => {
                this.taskService.getAll(t).subscribe(ts => {
                    this.tasks = ts;
                    this.scheduledService.getAll(t).subscribe(scheduleds => {
                        this.scheduleds = scheduleds;
                        this.scheduleds.forEach(scheduled => {
                            this.tasks.forEach(task => {
                                if (task.id === scheduled.taskId) {
                                    this.scheduledTasks.push(new ScheduledTask(scheduled.id, task.text, scheduled.from, scheduled.periodicity));
                                }
                            });
                        });
                        this.scheduledService.getAll(t).subscribe(scheduleds => {
                            this.active.forEach(act => {
                                this.scheduledTasks.forEach(task => {
                                    if (act.scheduledId === task.id) {
                                        this.activities.push(new ActivityUser(act.id, task.text, act.complete, act.date));
                                    }
                                });
                            });
                        });
                    });
                });
            });
        });
    }
}
