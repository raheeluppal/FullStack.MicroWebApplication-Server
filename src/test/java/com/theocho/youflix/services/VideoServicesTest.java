package com.theocho.youflix.services;

import com.theocho.youflix.YouflixApplication;
import com.theocho.youflix.models.Video;
import com.theocho.youflix.repositories.VideoRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = YouflixApplication.class)
public class VideoServicesTest {

    @Mock
    private VideoRepository videoRepository;

    @InjectMocks
    private VideoServices videoServices;

    Video testVideo;
    Video otherVideo;

    @Before
    public void setUp() throws Exception {
        testVideo = new Video("sample title", "sample description", "sample url");
        testVideo.setId(1L);

        otherVideo = new Video("other title", "other description","other url");
        otherVideo.setId(2L);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void create_a_video_Test() {
        // Given
        when(videoRepository.save(testVideo)).thenReturn(testVideo);
        // When
        Video actualVideo = videoServices.create(testVideo);
        // Then
        verify(videoRepository, times(1)).save(testVideo);
        Assert.assertEquals(testVideo, actualVideo);
    }

    @Test
    public void show_one_video_Test(){
        // Given
        Long testVideoId = 1L;
        when(videoRepository.findById(testVideoId)).thenReturn(java.util.Optional.ofNullable(testVideo));
        // When
        Video actualVideo = videoServices.showOne(testVideoId);
        // Then
        verify(videoRepository, times(1)).findById(testVideoId);
        Assert.assertEquals(testVideo, actualVideo);
    }

    @Test (expected = NullPointerException.class)
    public void passing_invalid_id_to_show_one_video_Test() throws IOException {
        // Given
        Long otherVideoID = -1L;
        when(videoRepository.findById(otherVideoID)).thenReturn(null);
        // When
        Video actualVideo = videoServices.showOne(otherVideoID);
        // Then
        verify(videoRepository, times(1)).findById(otherVideoID);
        Assert.assertNull(actualVideo);
//        Assert.as(IOException, actualVideo);
    }

    @Test
    public void show_all_videos_Test(){
        // Given
        List<Video> expected = new ArrayList<>(Arrays.asList(testVideo, otherVideo));
        when(videoRepository.findAll()).thenReturn(stubVideoList());
        // When
        List<Video> actual = (List<Video>) videoServices.showAll();
        // Then
        verify(videoRepository, times(1)).findAll();
        Assert.assertEquals(expected,actual);
    }

    private List<Video> stubVideoList(){
        return Arrays.asList(testVideo, otherVideo);
    }

}
