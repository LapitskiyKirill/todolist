import {Component, OnInit} from '@angular/core';
import {Category} from '../../dto/Category';
import {CategoryService} from '../../service/category.service';
import {TokenProvider} from '../../provider/token.provider';
import {EditTask} from '../../dto/EditTask';
import {TaskService} from '../../service/task.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Task} from 'src/app/dto/Task';
import * as moment from 'moment';
import {AppComponent} from '../../app.component';


@Component({
    selector: 'app-edit-task',
    templateUrl: './edit-task.component.html',
    styleUrls: ['./edit-task.component.scss']
})
export class EditTaskComponent implements OnInit {
    categories: Category[];
    editTask: EditTask = new EditTask('', null, '');
    taskId: number;
    task: Task;


    constructor(private app: AppComponent,
                private activatedRoute: ActivatedRoute,
                private categoryService: CategoryService,
                private tokenProvider: TokenProvider,
                private taskService: TaskService,
                private router: Router
    ) {

    }

    edit() {
        if (this.editTask.deadline === 'Invalid date') {
            this.editTask.deadline = null;
        }
        console.log(this.editTask);
        this.taskService.edit(localStorage.getItem('token'), this.taskId, this.editTask).subscribe();
        this.router.navigate(['tasks']);
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
                    this.editTask = new EditTask(this.task.text,
                        moment(this.task.deadline).format('YYYY-MM-DDTHH:mm'), this.task.category.value);
                });
            });
        });
    }
}
