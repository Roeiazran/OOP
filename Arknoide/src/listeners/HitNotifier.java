
package listeners;
/**
 *  Provides interface for the Subject.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public interface HitNotifier {

    /**
     * adds hitlistener to the hitlisteners array.
     * @param hl the hitlisterner.
     */
    void addHitListener(HitListener hl);

    /**
     * removes hitlistener to the hitlisteners array.
     * @param hl the hitlisterner.
     */
    void removeHitListener(HitListener hl);
}
