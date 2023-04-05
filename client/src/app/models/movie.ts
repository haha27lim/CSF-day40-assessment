export interface Movie {
    title: string;
    summary: string;
}

export interface Review {
    title: string;
    rating: string;
    byline: string;
    headline: string;
    summary: string;
    reviewURL: string;
    image?: string;
}

export interface Comment {
     name: string;
     rating: string;
     comment: string; 
}
  