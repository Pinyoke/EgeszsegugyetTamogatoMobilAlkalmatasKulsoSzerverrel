import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SellerSelectComponent } from './seller-select.component';

describe('SellerSelectComponent', () => {
  let component: SellerSelectComponent;
  let fixture: ComponentFixture<SellerSelectComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SellerSelectComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SellerSelectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
