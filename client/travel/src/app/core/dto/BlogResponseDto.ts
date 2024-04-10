export interface BlogResponseDto {
  id: number;
  createdAt: string;
  title: string;
  subtitle: string;
  content: string;
  thumbnailImageId: number;
  username: string;
  tourTitle: string;
  tagNames: string[];
}
