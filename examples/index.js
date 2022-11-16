db.doc1.aggregate([
  { $match: { _id: ObjectId('5901a4c63541b7d5d3293766') } },
  {
    $lookup:
      {
        from: 'doc2',
        localField: '_id',
        foreignField: 'userId',
        as: 'address'
      }
  },
  {
    $unwind: '$address'
  },
  {
    $project: {
      __v: 0,
      'address.__v': 0,
      'address._id': 0,
      'address.userId': 0,
      'address.mob': 0
    }
  },
  {
    $lookup:
      {
        from: 'doc3',
        localField: '_id',
        foreignField: 'userId',
        as: 'social'
      }
  },
  {
    $unwind: '$social'
  },

  {
    $project: {
      __v: 0,
      'social.__v': 0,
      'social._id': 0,
      'social.userId': 0
    }
  }

])
  .pretty();