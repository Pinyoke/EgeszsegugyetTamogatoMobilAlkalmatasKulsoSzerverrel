import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ContactPointCreateComponent } from './contact-point-create.component';

describe('ContactPointCreateComponent', () => {
  let component: ContactPointCreateComponent;
  let fixture: ComponentFixture<ContactPointCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ContactPointCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ContactPointCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
