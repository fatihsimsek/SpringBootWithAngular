import { Injectable } from '@angular/core';       
import { ErrorHandler } from '@angular/core';

@Injectable()
export class GlobalErrorHandler implements ErrorHandler {
    
    constructor() {}
    
    handleError(error) {
        const message = error.message ? error.message : error.toString();
        window.alert('Error: ' + message);
    }
}