import {User} from './user';
import {Post} from './post';

export class Comments{
  id: number;
  txt: string;
  date: Date;
  user: User;
  post: Post;
}
