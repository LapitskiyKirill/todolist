import {Component, Input, OnInit, Output} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {TokenProvider} from '../../provider/token.provider';
import {TaskService} from '../../service/task.service';
import {Task} from '../../dto/Task';
import {AppComponent} from '../../app.component';
import * as moment from 'moment';

@Component({
    selector: 'app-category-tasks',
    templateUrl: './category-tasks.component.html',
    styleUrls: ['./category-tasks.component.scss']
})
export class CategoryTasksComponent implements OnInit {
    tasks: Task[];
    @Output('currentCategoryName')
    currentCategoryName: string;
    tasksId: number[] = [];

    constructor(private app: AppComponent,
                private activatedRoute: ActivatedRoute,
                private taskService: TaskService,
                private tokenProvider: TokenProvider
    ) {

    }

    ngOnInit() {
        this.app.onLoad(() => {
            this.activatedRoute.params.subscribe(params => {
                this.currentCategoryName = params['categoryName'];
            });
            this.tokenProvider.token.subscribe  (t => {
                this.taskService.getByCategory(t, this.currentCategoryName).subscribe(ts => {
                    this.tasks = ts;
                    ts.forEach(task => {
                        if ((moment(moment(Date.now()).format('YYYY-MM-DD')).isAfter(task.deadline)
                            || moment(Date.now()).format('YYYY-MM-DD') === (moment(task.deadline).format('YYYY-MM-DD')))
                            && task.completed === null) {
                            this.tasksId.push(task.id);
                        }
                    });
                    console.log(this.tasksId);
                });
            });
        });
    }
}
