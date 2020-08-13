import { TestBed } from '@angular/core/testing';

import { BasicAuthHtppInterceptorServiceServiceServiceService } from './basic-auth-htpp-interceptor-service-service-service.service';

describe('BasicAuthHtppInterceptorServiceServiceServiceService', () => {
  let service: BasicAuthHtppInterceptorServiceServiceServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BasicAuthHtppInterceptorServiceServiceServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
