import { ChangeDetectionStrategy, Component } from '@angular/core';
import { GoogleMap } from '@angular/google-maps';
import { RouterOutlet } from '@angular/router';
import { YouTubePlayer } from '@angular/youtube-player';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { AuthService } from '@auth0/auth0-angular';

@Component({
  selector: 'app-root',
  imports: [GoogleMap, MatButtonModule, MatCardModule, RouterOutlet, YouTubePlayer],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class AppComponent {
  title = 'frontend-angular';

  constructor(public auth: AuthService) {}
}
