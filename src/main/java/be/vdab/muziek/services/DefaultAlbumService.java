package be.vdab.muziek.services;

import be.vdab.muziek.domain.Album;
import be.vdab.muziek.repositories.AlbumRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultAlbumService implements AlbumService
{
    private final AlbumRepository albumRepository;

    //CONSTRUCTORS
    DefaultAlbumService(AlbumRepository albumRepository)
    {
        this.albumRepository = albumRepository;
    }
    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    @Override
    public Optional<Album> findById(long id) {
        return albumRepository.findById(id);
    }
}
