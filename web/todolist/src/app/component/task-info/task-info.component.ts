import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Category} from '../../dto/Category';
import {CategoryService} from '../../service/category.service';
import {TokenProvider} from '../../provider/token.provider';
import {Task} from 'src/app/dto/Task';
import {TaskService} from '../../service/task.service';
import {AppComponent} from '../../app.component';

@Component({
    selector: 'app-task-info',
    templateUrl: './task-info.component.html',
    styleUrls: ['./task-info.component.scss']
})
export class TaskInfoComponent implements OnInit {
    categories: Category[];
    taskId: number;
    task: Task;

    constructor(private app: AppComponent,
                private activatedRoute: ActivatedRoute,
                private categoryService: CategoryService,
                private taskService: TaskService,
                private tokenProvider: TokenProvider
    ) {
    }

    delete() {
        this.tokenProvider.token.subscribe(t => {
            this.taskService.delete(t, this.taskId).subscribe();
        });
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

    ngOnInit() {
        this.app.onLoad(() => {
            this.activatedRoute.params.subscribe(params => this.taskId = params['taskId']);
            this.tokenProvider.token.subscribe(t => {
                this.categoryService.getCategories(t).subscribe(cs => {
                    this.categories = cs;
                });
                this.taskService.getAll(t).subscribe(ts => {
                    ts.forEach(t => {
                        if (t.id == this.taskId) {
                            this.task = t;
                        }
                    });
                });
            });
        });
    }
}
