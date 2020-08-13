import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductSelectComponentComponent } from './product-select-component.component';

describe('ProductSelectComponentComponent', () => {
  let component: ProductSelectComponentComponent;
  let fixture: ComponentFixture<ProductSelectComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductSelectComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductSelectComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
