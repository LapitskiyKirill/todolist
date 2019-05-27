import {Component, OnInit} from '@angular/core';
import {Category} from '../../dto/Category';
import {Task} from '../../dto/Task';
import {ActivatedRoute, Router} from '@angular/router';
import {CategoryService} from '../../service/category.service';
import {TaskService} from '../../service/task.service';
import {TokenProvider} from '../../provider/token.provider';
import {NewCategory} from '../../dto/NewCategory';

@Component({
    selector: 'app-add-category',
    templateUrl: './add-category.component.html',
    styleUrls: ['./add-category.component.scss']
})
export class AddCategoryComponent implements OnInit {
    newCategory: NewCategory = new NewCategory();
    categories: Category[];

    constructor(
        private categoryService: CategoryService,
        private tokenProvider: TokenProvider,
        private router: Router
    ) {
    }

    create() {
        if (this.newCategory.value !== null) {
            console.log(this.newCategory);
            this.router.navigate(['/tasks']);
            this.tokenProvider.token.subscribe(t => {
                this.categoryService.create(t, this.newCategory).subscribe();

            });
        }
    }

    ngOnInit() {
        this.tokenProvider.token.subscribe(t => {
            this.categoryService.getCategories(t).subscribe(cs => {
                this.categories = cs;
            });


        });
    }

}
