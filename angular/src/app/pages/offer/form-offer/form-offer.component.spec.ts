import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormOfferComponent } from './form-offer.component';

describe('FormOfferComponent', () => {
  let component: FormOfferComponent;
  let fixture: ComponentFixture<FormOfferComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormOfferComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormOfferComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
