import {User} from './user';
import {Comments} from './comments';
import {Tag} from './tag';
import {Likee} from './likee';

export class Post{

  id: number;
  photo: number;
  txt: string;
  date: string;
  user: User;
  comment: Comments[];
  tag: Tag[];
  like: Likee[];
}
