using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Retry : MonoBehaviour
{
    public void RestartGame()
    {
        SceneManager.LoadScene("Scenee");
        SoundManager.instance.musicFinal.Stop();
        SoundManager.instance.musicWin.Stop();
        SoundManager.instance.musicSource.Play();


    }
}
