import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FoodIngredientCreateComponent } from './food-ingredient-create.component';

describe('FoodIngredientCreateComponent', () => {
  let component: FoodIngredientCreateComponent;
  let fixture: ComponentFixture<FoodIngredientCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FoodIngredientCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FoodIngredientCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
