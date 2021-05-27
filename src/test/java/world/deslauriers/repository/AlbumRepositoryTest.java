package world.deslauriers.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import world.deslauriers.domain.Album;

@MicronautTest
public class AlbumRepositoryTest {
	
	@Inject
	private final AlbumRepository albumDao;

	public AlbumRepositoryTest(AlbumRepository albumDao) {
		this.albumDao = albumDao;
	}
	
	@Test
	void testAlbumDaoCrud() {
		
		var judo = new Album("Judo");
		
		judo = albumDao.save(judo);
		assertNotNull(judo.getId());
		assertEquals("Judo", judo.getAlbum());
		
		var find = albumDao.findById(judo.getId()).get();
		assertNotNull(find.getId());
		assertEquals("Judo", find.getAlbum());
		
		find.setAlbum("Art");
		var update = albumDao.update(find);
		assertEquals("Art", update.getAlbum());
		
		var find1 = albumDao.findById(update.getId()).get();
		assertEquals("Art", find1.getAlbum());
		
		var albums = albumDao.findAll();
		assertTrue(albums.iterator().hasNext());
		albums.forEach(album -> System.out.println(album.toString()));
		
		albumDao.deleteById(find.getId());
		var deleted = albumDao.findById(find.getId());
		assertTrue(deleted.isEmpty());
	}
}
