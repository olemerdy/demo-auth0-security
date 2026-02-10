import { Component, inject } from '@angular/core';
import { AuthService } from '@auth0/auth0-angular';

@Component({
  selector: 'app-logout-button',
  imports: [],
  templateUrl: './logout-button.html',
  styleUrl: './logout-button.css',
})
export class LogoutButton {
  private auth = inject(AuthService);

  logout(): void {
    this.auth.logout({
      logoutParams: {
        returnTo: window.location.origin
      }
    });
  }
}
