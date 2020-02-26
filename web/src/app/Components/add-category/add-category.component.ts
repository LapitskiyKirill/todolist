import {CategoriesService} from '../../Services/categories.service';
import {NewCategory} from '../../Entities/NewCategory';
import {Router} from '@angular/router';
import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.scss']
})
export class AddCategoryComponent implements OnInit {
  newCategory: NewCategory = new NewCategory();

  constructor(
    private router: Router,
    private categoryService: CategoriesService
  ) {
  }

  ngOnInit() {
  }

  createCategory() {
    this.categoryService.createCategory(localStorage.getItem('token'), this.newCategory)
      .subscribe(() => this.router.navigate(['/main/categories']));
  }
}
