package be.vdab.muziek.repositories;

import be.vdab.muziek.domain.Album;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// enkele imports
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JpaAlbumRepository.class)
@Sql("/insertArtiest.sql")
@Sql("/insertAlbum.sql")
public class JpaAlbumRepositoryTest
        extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String ALBUMS = "albums";
    @Autowired
    private JpaAlbumRepository repository;
    @Autowired
    private EntityManager manager;
    private long idVanTestAlbum() {
        return super.jdbcTemplate.queryForObject(
                "select id from albums where naam='test'", Long.class);
    }
    @Test
    public void findById() {
        Album album = repository.findById(idVanTestAlbum()).get();
        assertThat(album.getNaam()).isEqualTo("test");
        assertThat(album.getArtiest().getNaam()).isEqualTo("test");
        assertThat(album.getTijd()).isEqualByComparingTo(BigDecimal.TEN);
    }
    @Test
    public void findByOnbestaandeId() {
        assertThat(repository.findById(-1)).isNotPresent();
    }
    @Test
    public void findAll() {
        List<Album> albums = repository.findAll();
        manager.clear();
        assertThat(albums).hasSize(super.countRowsInTable(ALBUMS))
                .extracting(album -> album.getNaam().toLowerCase()).isSorted();
        assertThat(albums).extracting(album -> album.getArtiest().getNaam());
    }
}

