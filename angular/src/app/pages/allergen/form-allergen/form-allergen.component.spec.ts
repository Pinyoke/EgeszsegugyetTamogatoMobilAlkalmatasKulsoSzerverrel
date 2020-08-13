import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormAllergenComponent } from './form-allergen.component';

describe('FormAllergenComponent', () => {
  let component: FormAllergenComponent;
  let fixture: ComponentFixture<FormAllergenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormAllergenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormAllergenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
