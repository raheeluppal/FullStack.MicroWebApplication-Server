package com.theocho.youflix.services;


import com.theocho.youflix.models.Video;
import com.theocho.youflix.repositories.VideoRepository;
import org.springframework.stereotype.Service;

@Service
public class VideoServices {

    private final VideoRepository videoRepository;

    public VideoServices(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public Video create(Video video) {
        return videoRepository.save(video);
    }

    public Video showOne(Long id) {
        return videoRepository.findById(id).get();
    }

    public Iterable<Video> showAll(){
        return videoRepository.findAll();
    }

}
