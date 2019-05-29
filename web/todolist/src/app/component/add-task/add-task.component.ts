import {Component, OnInit} from '@angular/core';
import {Category} from '../../dto/Category';
import {AppComponent} from '../../app.component';
import {ActivatedRoute, Router} from '@angular/router';
import {CategoryService} from '../../service/category.service';
import {TokenProvider} from '../../provider/token.provider';
import {TaskService} from '../../service/task.service';
import {NewTask} from '../../dto/NewTask';


@Component({
    selector: 'app-add-task',
    templateUrl: './add-task.component.html',
    styleUrls: ['./add-task.component.scss']
})
export class AddTaskComponent implements OnInit {
    categories: Category[];
    newTask: NewTask = new NewTask();

    constructor(private app: AppComponent,
                private activatedRoute: ActivatedRoute,
                private categoryService: CategoryService,
                private tokenProvider: TokenProvider,
                private taskService: TaskService,
                private router: Router) {
    }

    create() {
        if (this.newTask.deadline === 'Invalid date') {
            this.newTask.deadline = null;
        }
        console.log(this.newTask.category);
        this.newTask.subTasks = [];
        if (this.newTask.category === undefined) {
            this.newTask.category = 'Other';
        }
        this.taskService.create(localStorage.getItem('token'), this.newTask).subscribe();
    }

    ngOnInit() {
        this.app.onLoad(() => {
            this.tokenProvider.token.subscribe(t => {
                this.categoryService.getCategories(t).subscribe(cs => {
                    this.categories = cs;
                });
            });
        });
    }
}
