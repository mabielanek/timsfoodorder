
    var albumsDB = [
      {
        id: 1,
        artist: 'Rihanna',
        title: 'Loud',
        releaseYear: '2010',
        comment: 'Loud is a departure from the personal, melodramatic themes of Rated R. Stylistically, it is a return to the Caribbean-inspired dance-pop of Rihanna\'s earlier work. In an interview with MTV, Rihanna said "I wanted songs that were all Rihanna songs, that nobody else could do. I didn\'t want the generic pop record that Ke$ha or Lady Gaga or Katy Perry could just do and it\'ll work.',
        songs: [
          {
            trackNumber: 1,
            title: 'S&M',
            time: {minutes: 4, seconds: 3}
          },
          {
            trackNumber: 2,
            title: 'What\'s My Name?',
            time: {minutes: 4, seconds: 23}
          },
          {
            trackNumber: 3,
            title: 'Cheers',
            time: {minutes: 4, seconds: 21}
          },
          {
            trackNumber: 4,
            title: 'Fading',
            time: {minutes: 3, seconds: 27}
          }
        ]
      },
      {
        id: 2,
        artist: 'Paul McCartney',
        title: 'New',
        releaseYear: '2013',
        comment: 'McCartney has said that the album would be "very varied. I worked with four producers and each of them brought something different". The songs produced by Paul Epworth "weren\'t written" but improvised. The title track, "New", is a "love song but it\'s saying, \'Don\'t look at me, I haven\'t got any answers.\' It says, \'I don\'t know what\'s happening, I don\'t know how it\'s all happening, but it\'s good and I love you.\'"',
        songs: [
          {
            trackNumber: 1,
            title: 'Save Us',
            time: {minutes: 2, seconds: 39}
          },
          {
            trackNumber: 2,
            title: 'Alligator',
            time: {minutes: 3, seconds: 27}
          },
          {
            trackNumber: 3,
            title: 'On My Way To Work',
            time: {minutes: 3, seconds: 43}
          },
          {
            trackNumber: 4,
            title: 'Queenie Eye',
            time: {minutes: 3, seconds: 48}
          },
          {
            trackNumber: 5,
            title: 'Early Days',
            time: {minutes: 4, seconds: 7}
          }
        ]
      },
      {
        id: 3,
        artist: 'Pearl Jam',
        title: 'Lightning Bolt',
        releaseYear: '2013',
        comment: 'Lightning Bolt contains songs with lyrics discussing lasting relationships, bad faith ("Getaway," "Mind Your Manners"), the state of the world ("Infallible") and life\'s transience ("Pendulum"). Gossard explained that the reflective tone was indicative of the bandmembers\' age: "[At] 40-something, almost 50-something, you\'re looking at life through your kids\' eyes, through the filter of relationships that are 20 or 30 years long, through the filter of your parents getting older and the passing of friends and relatives-relationships and all that they encompass, the difficulties of them and the sacrifices you make in them and also the joy they bring you.',
        songs: [
          {
            trackNumber: 1,
            title: 'Getaway',
            time: {minutes: 3, seconds: 26}
          },
          {
            trackNumber: 2,
            title: 'Mind Your Manners',
            time: {minutes: 2, seconds: 38}
          },
          {
            trackNumber: 3,
            title: 'My Father\'s Son',
            time: {minutes: 3, seconds: 7}
          },
          {
            trackNumber: 4,
            title: 'Sirens',
            time: {minutes: 5, seconds: 41}
          }
        ]
      },
      {
        id: 4,
        artist: 'Charles Bradley',
        title: 'Victim of Love',
        releaseYear: '2013',
        comment: 'Victim of Love received universal acclaim from music critics. At Metacritic, which assigns a rating out of 100 to reviews from mainstream critics, the album received an average score of 81, based on 20 reviews',
        songs: [
          {
            trackNumber: 1,
            title: 'Strictly Reserved For You',
            time: {minutes: 3, seconds: 43}
          },
          {
            trackNumber: 2,
            title: 'You Put the Flame On It',
            time: {minutes: 3, seconds: 49}
          },
          {
            trackNumber: 3,
            title: 'Let Love Stand A Chance',
            //time: {minutes: 3, seconds: 59}
          }
        ]
      }
    ];

function getSongs(albumId) {
  for(var idx = 0; idx < albumsDB.length; idx++) {
    if(albumsDB[idx].id == albumId) {
      return albumsDB[idx].songs;
    }
  }
  return [];
}
  
function getAlbum(albumId) {
  for(var idx = 0; idx < albumsDB.length; idx++) {
    if(albumsDB[idx].id == albumId) {
      return albumsDB[idx];
    }
  }
  return {};
}


module.exports = {

  getAlbums : function(req, res) {
    res.json(
      {
        "result" : albumsDB 
      }
    );
  },
  
  getAlbumById : function(req, res) {
    res.json({
      result: getAlbum(req.params.id)
    });
  },
  
  getSongsByAlbumId : function(req, res) {
    res.json({
      result: getSongs(req.params.id)
    });
  },
  
  saveAlbum: function(req, res) {
    var albumId = req.body.id;
    var album = getAlbum(albumId);
    if(album.id) {
      album.artist = req.body.artist;
      album.title = req.body.title;
      album.releaseYear = req.body.releaseYear;
      album.comment = req.body.comment;
      album.songs = req.body.songs;
    }
    res.json({
      result: album,
    });
  }
  
};