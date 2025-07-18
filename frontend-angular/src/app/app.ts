import { ChangeDetectionStrategy, Component } from '@angular/core';
import { GoogleMap } from '@angular/google-maps';
import { RouterOutlet } from '@angular/router';
import { YouTubePlayer } from '@angular/youtube-player';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-root',
  imports: [GoogleMap, MatButtonModule, MatCardModule, RouterOutlet, YouTubePlayer],
  templateUrl: './app.html',
  styleUrl: './app.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class App {
  title = 'frontend-angular';
}
